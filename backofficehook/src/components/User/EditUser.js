import { useEffect, useState ,useContext} from 'react';
import {Context} from '../../contexts/Context';
import { useHistory } from 'react-router-dom';
import UserService from '../../services/UserService';
import HeaderComponent from '../HeaderComponent';
import Loading from "../Loading";
const EditUser=(props)=>{
    const{users}=useContext(Context);
    const[loading,setLoading]=useState(true);
    const history = useHistory();
    const[user,setUser]=useState({
        id:history.location.state?.id,
        username:'',
        password:'',
        rolesId:[]
    })
    const[roles,setRoles]=useState([]);
    const{id,username,password,rolesId} = user;
    
    async function getAllRoles(){
        const res = await UserService.listAllRoles(users);
        if(res.status=='200'){
            setRoles(res.data);
        }
    }
    async function getUserById(){
        const res = await UserService.getUserById(id,users);
        if(res.status=='200'){
            setUser({...user,username:res.data.username,password:res.data.password,rolesId:res.data.rolesId})
        }
    }

    async function getData(){
        await getAllRoles();
        await getUserById();
        setLoading(false)

    }

    useEffect(()=>{
        getData();
    },[])

    const changeHandler=(e)=>{
        setUser({...user,[e.target.name]:e.target.value});
    }

    const changeMultiCate=(id)=>{
        if (rolesId.includes(id) !== true) {
            rolesId.push(id);
        } else {
            for (let i = 0; i < rolesId.length; i++) {
                if (id === rolesId[i]) {
                    rolesId.splice(i, 1);
                }
            }
        }
    }

    const updateUser=async (e)=>{
        e.preventDefault();
        let user={
            id:id,
            username:username,
            password:password,
            rolesId:rolesId
        }
        const res = await UserService.updateUser(user,users);
        if(res.status=='200'){
            history.push('/users');
        }
    }

    const cancel=(e)=>{
        history.push('/users');
    }

    const multiRole=(e)=>{
        const selected = [];
        for (let i = 0; i < roles.length; i++) {
            if (rolesId.includes(roles[i].id)) {
                selected.push(<div className="row col-md -12" key={roles[i].id}><label className="col-md-12"  ><input type="checkbox" defaultChecked="true"
                                                                                                    onClick={(e) => changeMultiCate(roles[i].id)}/>{roles[i].name}
                </label></div>)
            } else {
                selected.push(<div className="row col-md -12" key={roles[i].id}><label className="col-md-12"><input  type="checkbox"
                                                                                                   onClick={(e) => changeMultiCate(roles[i].id)}/>{roles[i].name}
                </label></div>)
            }

        }
        return selected;
    }

    return(
        <>
            {loading ==true?
            <Loading/> :   
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
                                   {multiRole()}
                                </div>
                            </div>
                            <button className="btn btn-success" onClick={(e)=>updateUser(e)}>Save</button>
                            <button className="btn btn-danger" onClick={(e)=>cancel(e)}
                                    style={{marginLeft: "10px"}}>Cancel
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
       }
       </>
    )
}
export default EditUser;