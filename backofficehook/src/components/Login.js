import { useContext,  useEffect,  useRef,  useState } from 'react';
import {Context} from '../contexts/Context';
import { useHistory } from 'react-router-dom';
const Login =()=>{
    const history = useHistory();
    const refCheck = useRef();
    const[checked,setChecked]=useState({checked:false});
    const {login} = useContext(Context);
    
    const [user,setUser]=useState({username:'',password:''})

    const {username,password} = user;
    
    const onChange=(e)=>{
        setUser({...user,[e.target.name]:e.target.value})
    }

    const loginUser = async (e)=>{
        e.preventDefault();
        const res =await login(username,password);
        if(res.status=='200'){
            await rememberFunc();
            history.push('/categories');
        }
        else{
            console.log("Hatalı Giriş Yaptınız...!");
        }
    } 

    const loadRemember=(e)=>{
        if(localStorage.getItem("rememberUser")!==null && localStorage.getItem("rememberPass")!==null){
            setUser({...user,username:localStorage.getItem("rememberUser"),password:localStorage.getItem("rememberPass")})
            setChecked({...checked,checked:true})
            document.getElementById("check").checked=true;
        }
        else{
            document.getElementById("check").checked=false;
            setChecked({...checked,checked:false})
        }
    }

    const rememberFunc=(e)=>{
        if(checked.checked===true){
            localStorage.setItem("rememberUser",username);
            localStorage.setItem("rememberPass",password);
        }else{
            localStorage.removeItem("rememberUser");
            localStorage.removeItem("rememberPass");
            setChecked({...checked,checked:false})
        }
    }

    const addLocal=(event) =>{
        console.log(event.target.checked);
        setChecked({...checked,checked:event.target.checked})
    }

    useEffect(()=>{
        loadRemember();
    },[])
    
    return(
        <div>
                <div>
                    <div className="container">
                        <div className="row" style={{marginTop:"10rem"}}>
                            <div className="card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Login</h3>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label> username </label>
                                            <input placeholder="username" name="username" className="form-control"
                                                   value={username} onChange={(e)=>onChange(e)}/>
                                        </div>
                                        <div className="form-group">
                                            <label> password </label>
                                            <input placeholder="password" name="password" className="form-control" type="password"
                                                   value={password} onChange={(e)=>onChange(e)}/>
                                        </div>
                                        <div className="form-group">
                                            <label> Remember Me </label>
                                            <label><input id="check" ref={refCheck} type="checkbox"
                                                       onChange={(e)=>addLocal(e)} />
                                            </label>
                                        </div>
                                        <button className="btn btn-success" onClick={(e)=>loginUser(e)}>Login</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    )
}
export default Login;