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


    addUser(user,users){
        return axios.post(USER_API_BASE_URL+"control/add/",user,{
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }


    listAllUser(users){
        return axios.get(USER_API_BASE_URL+"control/all/", {
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }


    deleteUser(id,users){
        return axios.delete(USER_API_BASE_URL+"control/delete/"+id, {
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }


    getUserById(id,users){
        return axios.get(USER_API_BASE_URL+"control/"+id, {
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }


    updateUser(User,users){
        return axios.put(USER_API_BASE_URL+"control/update/",User, {
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }

    listAllRoles(users){
        return axios.get(ROLE_API_BASE_URL+"list",{
            auth: {
                username: users.username,
                password: users.password
            }
        })
    }
    deleteRole(id,users){
        return axios.delete(ROLE_API_BASE_URL+"delete/"+id,{
            auth: {
                username: users.username,
                password: users.password
            }
        })
    }

    addRole(role,users){
        return axios.post(ROLE_API_BASE_URL+"add/",role,{
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }

    updateRole(role,users){
        return axios.put(ROLE_API_BASE_URL+"update/",role,{
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }

    getRoleById(id,users){
        return axios.get(ROLE_API_BASE_URL+id,{
            auth: {
                username: users.username,
                password: users.password
            }
        })
    }

}
export default new  UserService()