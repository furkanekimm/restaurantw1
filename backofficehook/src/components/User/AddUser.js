import { useEffect, useState ,useContext} from 'react';
import {Context} from '../../contexts/Context';
import { useHistory } from 'react-router-dom';
import UserService from '../../services/UserService';
import HeaderComponent from '../HeaderComponent';
const AddUser=(props)=>{
    const{users}=useContext(Context);
    const history = useHistory();
    const[user,setUser]=useState({
        username:'',
        password:'',
        rolesId:[]
    })
    const[roles,setRoles]=useState([]);
    const{username,password,rolesId} = user;
    
    async function getAllRoles(){
        const res = await UserService.listAllRoles(users);
        if(res.status=='200'){
            setRoles(res.data);
        }
    }

    useEffect(()=>{
        getAllRoles();
    },[])

    const changeHandler=(e)=>{
        setUser({...user,[e.target.name]:e.target.value});
    }

    const changeMultiCate=(id)=>{
        if (rolesId.includes(id) !== true) {
            rolesId.push(id);
        } else {
            for (let i = 0; i < rolesId.length; i++) {
                if (id == rolesId[i]) {
                    rolesId.splice(i, 1);
                }
            }
        }
    }

    const saveUser=async (e)=>{
        e.preventDefault();
        let user={
            username:username,
            password:password,
            rolesId:rolesId
        }
        const res = await UserService.addUser(user,users);
        if(res.status=='200'){
            history.push('/users');
        }
    }

    const cancel=(e)=>{
        history.push('/users');
    }


    return(
        <div>
        <HeaderComponent/>
        <div className="container">

            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Add User</h3>
                    <div className="card-body">
                        <form>
                            <div className="form-group">
                                <label> username </label>
                                <input placeholder="username" name="username" className="form-control"
                                       value={username} onChange={(e)=>changeHandler(e)}/>
                            </div>
                            <div className="form-group">
                                <label> password </label>
                                <input placeholder="password" name="password" className="form-control" type="password"
                                       value={password} onChange={(e)=>changeHandler(e)}/>
                            </div>

                            <div className="form-group">
                                <label> Category </label>
                                <div className="checkbox" style={{height: "4rem", overflow: "auto"}}>
                                    {
                                        roles.map(
                                            role =>
                                                <div className="row col-md -12" key={role.id}>
                                                    <label><input type="checkbox" value=""
                                                                  onClick={(e) => changeMultiCate(role.id)}/>{role.name}
                                                    </label>
                                                </div>
                                        )
                                    }
                                </div>
                            </div>
                            <button className="btn btn-success" onClick={(e)=>saveUser(e)}>Save</button>
                            <button className="btn btn-danger" onClick={(e)=>cancel(e)}
                                    style={{marginLeft: "10px"}}>Cancel
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    )
}
export default AddUser;