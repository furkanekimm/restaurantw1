import { useEffect, useState ,useContext} from 'react';
import {Context} from '../../contexts/Context';
import { useHistory } from 'react-router-dom';
import UserService from '../../services/UserService';
import HeaderComponent from '../HeaderComponent';
import Loading from "../Loading";
const EditRole = (props) => {
    const{users}=useContext(Context);
    const [loading, setLoading] = useState(true);
    const history = useHistory();
    const [role, setRole] = useState({
        id: history.location.state?.id,
        name: ''
    })
    const { id, name } = role;

    const changeHandler = (e) => {
        setRole({ ...role, [e.target.name]: e.target.value });
    }

    const editRole = async (e) => {
        e.preventDefault();
        let role = {
            id: id,
            name: name
        }
        const res = await UserService.updateRole(role,users);
        if (res.status == '200') {
            history.push('/roles')
        }
    }

    const cancel = (e) => {
        history.push('/roles');
    }

    async function getRoleById() {
        const res = await UserService.getRoleById(id,users);
        if (res.status == '200') {
            setRole({ ...role, name: res.data.name })
        }
        setLoading(false)
    }

    useEffect(() => {
        getRoleById();
    }, [])

    return (
        <div>
            {loading == true ?
                <Loading /> :
                <div>
                    <HeaderComponent />
                    <div className="container">
                        <div className="row">
                            <div className="card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Add Role</h3>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label> Role Name </label>
                                            <input placeholder="Table Name" name="name" className="form-control"
                                                value={name} onChange={(e) => changeHandler(e)} />
                                        </div>
                                        <button className="btn btn-success" onClick={(e) => editRole(e)}>Save</button>
                                        <button className="btn btn-danger" onClick={(e) => cancel(e)}
                                            style={{ marginLeft: "10px" }}>Cancel
                                </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            }
        </div>
    )
}
export default EditRole;