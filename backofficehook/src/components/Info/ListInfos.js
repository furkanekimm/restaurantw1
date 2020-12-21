import { useEffect, useState,useContext } from 'react';
import {Context} from '../../contexts/Context';
import HeaderComponent from '../HeaderComponent';
import InfoService from '../../services/InfoServices';
import Loading from "../Loading";
import {useHistory} from 'react-router-dom';
const ListInfos = (props) => {
    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [infos, setInfos] = useState([]);
    const{users,authorizeControl}=useContext(Context);
    async function getAllInfos() {
        const controlContext =await authorizeControl(); 
        if(await controlContext===false){
            history.push('/');
        }
        const res = await InfoService.getInfo(users);
        if (res.status == '200') {
            setInfos(res.data);
        }
        setLoading(false)
    }

    useEffect(() => {
        getAllInfos();
    }, [])
    return (
        <>
            {loading == true ?
                <Loading /> :
                <div>
                    <HeaderComponent />
                    <div className="container" style={{ marginTop: "15px" }}>
                        <div className="row">
                            <table className="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>KEY</th>
                                        <th>VALUE</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        infos.map(
                                            info =>
                                                <tr>
                                                    <td>{info.key}</td>
                                                    <td>{info.value}</td>
                                                </tr>
                                        )
                                    }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            }
        </>
    )
}
export default ListInfos;