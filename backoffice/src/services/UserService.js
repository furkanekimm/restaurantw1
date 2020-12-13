import axios from 'axios';

const USER_API_BASE_URL = "http://localhost:8080/person/";
const ROLE_API_BASE_URL = "http://localhost:8080/role/";


class UserService{


    login(username,password){
        return axios.get(USER_API_BASE_URL+"login",{
            auth: {
                username: username,
                password: password
            }
        });
    }


    addUser(user){
        return axios.post(USER_API_BASE_URL+"control/add/",user,{
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }


    listAllUser(){
        return axios.get(USER_API_BASE_URL+"control/all/", {
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }


    deleteUser(id){
        return axios.delete(USER_API_BASE_URL+"control/delete/"+id, {
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }


    getUserById(id){
        return axios.get(USER_API_BASE_URL+"control/"+id, {
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }


    updateUser(User){
        return axios.put(USER_API_BASE_URL+"control/update/",User, {
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

    listAllRoles(){
        return axios.get(USER_API_BASE_URL+"control/allroles/",{
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }
    deleteRole(id){
        return axios.delete(ROLE_API_BASE_URL+"delete/"+id,{
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }

    addRole(role){
        return axios.post(ROLE_API_BASE_URL+"add/",role,{
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

    updateRole(role){
        return axios.put(ROLE_API_BASE_URL+"update/",role,{
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

}
export default new  UserService()