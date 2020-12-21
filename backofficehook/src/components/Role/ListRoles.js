import { useHistory } from 'react-router-dom';
import { Context } from '../../contexts/Context';
import HeaderComponent from '../HeaderComponent';
import UserService from '../../services/UserService';
import { useEffect, useState, useContext } from 'react';
import Loading from "../Loading";
const ListRoles = (props) => {
    const { users, authorizeControl } = useContext(Context);
    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [roles, setRoles] = useState([]);

    async function getAllRoles() {

        const controlContext = await authorizeControl();
        if (await controlContext === false) {
            history.push('/');
        }

        const res = await UserService.listAllRoles(users);
        if (res.status == '200') {
            setRoles(res.data);
        }
        setLoading(false)
    }

    useEffect(() => {
        getAllRoles();
    }, [])

    const goAddRole = (e) => {
        history.push('/addrole');
    }

    const editRole = (id) => {
        history.push({
            pathname: '/updaterole',
            state: {
                id: id
            }
        })
    }

    const deleteRole = async (id) => {
        const res = await UserService.deleteRole(id, users);
        if (res.status == '200') {
            setRoles(roles.filter(role => role.id !== id));
        }
    }

    return (
        <>
            {loading == true ?
                <Loading /> :
                <div>
                    <HeaderComponent />
                    <div className="container">
                        <div className="row">
                            <button style={{ marginTop: "15px", marginBottom: "10px" }} className="btn btn-primary" onClick={(e) => goAddRole(e)}>Add
                            Role
                </button>
                        </div>
                        <div className="row">
                            <table className="table table-striped table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Roles</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                {
                                    roles.map(
                                        role =>
                                            <tbody >
                                                <tr key={role.id}>
                                                    <td>{role.name}</td>
                                                    <td>
                                                        <button
                                                            onClick={(e) => editRole(role.id)}
                                                            className="btn btn-info"> Update
                                                    </button>
                                                        <button style={{ marginLeft: "6px" }}
                                                            onClick={(e) => deleteRole(role.id)}
                                                            className="btn btn-danger"> Delete
                                                    </button>
                                                    </td>
                                                </tr>

                                            </tbody>
                                    )
                                }
                            </table>
                        </div>
                    </div>
                </div>

            }
        </>
    )
}
export default ListRoles;