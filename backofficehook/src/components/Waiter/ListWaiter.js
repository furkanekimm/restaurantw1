import { useEffect, useState,useContext } from "react";
import {Context} from '../../contexts/Context';
import WaiterService from '../../services/WaiterService';
import { useHistory } from 'react-router-dom';
import HeaderComponent from '../HeaderComponent';
import Loading from "../Loading";
const ListWaiter = (props) => {
    const{users,authorizeControl}=useContext(Context);
    const [loading, setLoading] = useState(true);
    const history = useHistory();
    const [waiter, setWaiter] = useState([]);

    async function getAllWaiters() {
        const controlContext =await authorizeControl(); 
        if(await controlContext===false){
            history.push('/');
        }
        const res = await WaiterService.getAllWaiters(users);
        setWaiter(res.data);
        setLoading(false)
    }

    useEffect(() => {
        getAllWaiters();
    }, [])

    const goAddWaiter = (e) => {
        e.preventDefault();
        history.push('/addwaiter');
    }

    const editWaiter = (id) => {
        history.push({
            pathname: '/updatewaiter',
            state: {
                id: id
            }
        })
    }

    const deleteWaiter = async (id) => {
        const res = await WaiterService.deleteWaiter(id,users);
        if (res.status = '200') {
            setWaiter(waiter.filter(waiter => waiter.id !== id));
        }
    }

    return (
        <div>
            {loading == true ?
                <Loading /> :
                <div className="container">
                    <HeaderComponent />
                    <div className="row">
                        <button onClick={(e) => goAddWaiter(e)} className="btn btn-info ml-3 mt-2">Add Waiter</button>
                    </div>
                    <div className="table mt-3 ">
                        <table className="table table-striped border overflow-auto">
                            <thead >
                                <tr>
                                    <th>Image</th>
                                    <th>Name</th>
                                    <th>LastName</th>
                                    <th>PhoneNumber</th>
                                    <th>Email</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    waiter.map(
                                        waiter =>
                                            <tr>
                                                <td><img
                                                    style={{ marginLeft: "0rem", marginRight: "10px", borderRadius: "30px" }}
                                                    src={'data:image/png;base64,' + waiter.media.fileContent}
                                                    width="45rem" height="39rem"
                                                /></td>
                                                <td>{waiter.waiterName}</td>
                                                <td>{waiter.waiterLastName}</td>
                                                <td>{waiter.phoneNumber}</td>
                                                <td>{waiter.email}</td>

                                                <td>
                                                    <button
                                                        onClick={(e) => editWaiter(waiter.id)}
                                                        className="btn btn-info"> Update
                                        </button>
                                                    <button style={{ margin: "6px" }}
                                                        onClick={(e) => deleteWaiter(waiter.id)}
                                                        className="btn btn-danger"> Delete
                                        </button>
                                                </td>
                                            </tr>

                                    )
                                }
                            </tbody>
                        </table>
                    </div>
                </div>
            }
        </div>
    )
}
export default ListWaiter;