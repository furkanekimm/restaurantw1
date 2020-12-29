import { useEffect, useState, useContext } from 'react';
import { Context } from '../../contexts/Context';
import HeaderComponent from '../HeaderComponent';
import UserService from '../../services/UserService';
import { useHistory } from 'react-router-dom';
import Loading from "../Loading";

const ListUsers = (props) => {
    const { users, authorizeControl } = useContext(Context);
    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [user, setUser] = useState([]);

    async function getAllUsers() {
        const controlContext = await authorizeControl();
        if (await controlContext === false) {
            history.push('/');
        }
        const res = await UserService.listAllUser(users);
        await console.log(res);
        if (res.status == '200') {
            setUser(res.data);
        }
        setLoading(false)
    }

    useEffect(() => {
        getAllUsers();
    }, [])

    const addUser = (e) => {
        history.push('/adduser')
    }

    const editUser = (id) => {
        history.push({
            pathname: '/updateuser',
            state: {
                id: id
            }
        })
    }

    const deleteUser = async (id) => {
        const res = await UserService.deleteUser(id, users);
        if (res.status == '200') {
            setUser(user.filter(user => user.id !== id));
        }
    }

    return (
        <>
            {loading == true ?
                <Loading /> :
                <div >
                    <HeaderComponent />
                    <div className="container" >
                        <div className="row">
                            <button style={{ marginTop: "15px", marginBottom: "10px" }} className="btn btn-primary" onClick={(e) => addUser(e)} >Add User</button>
                        </div>
                        <div className="row">
                            <table className="table table-striped table-bordered">
                                <thead >
                                    <tr>
                                        <th>username</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        user.map(
                                            user =>
                                                <tr key={user.id}>
                                                    <td>{user.username}</td>
                                                    <td>
                                                        <button onClick={(e) => editUser(user.id)}
                                                            className="btn btn-info"> Update
                                    </button>
                                                        <button style={{ marginLeft: "6px" }} onClick={(e) => deleteUser(user.id)}
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
                </div>
            }
        </>
    )
}
export default ListUsers;