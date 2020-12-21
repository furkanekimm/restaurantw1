import {createContext, useState} from 'react';
import axios from 'axios';

export const Context = createContext();

const ContextProvider = (props) =>{
    const[users,setUsers] = useState({
        username:'',
        password:''
    })

    const{username,password}=users;
    const login = async (username,password) =>{
        const res = await axios.get("http://localhost:8080/person/login",{
            auth:{
                username: username,
                password: password
            }
        });
        if(res.status =='200'){
            setUsers({username,password});
        }else{
            console.log("hatalı giriş");
        }
        return res;
    }

    
   const authorizeControl=()=>{
        if(username=='' || password==''){
            return false;
        }else{
            return true;
        }
   }
    

    return(
        <Context.Provider value={{users,login,authorizeControl}}>
            {props.children}
        </Context.Provider>
    )
}

export default ContextProvider;