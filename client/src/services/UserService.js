import axios from 'axios';

const USER_API_BASE_URL = "http://localhost:8080/person/"



class UserService{


    login(username,password){
        return axios.get(USER_API_BASE_URL+"login",{
            params: {
                username: username,
                password: "{noop}"+password
            }
        });
    }


}
export default new UserService()