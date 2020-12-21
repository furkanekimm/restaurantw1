import axios from 'axios';
import {Context} from '../contexts/Context';
const INFO_BASE_URL = "http://localhost:8080/info/";

class InfoServices{
    static contextType = Context;
    getInfo(users){
        console.log(Context)
        return axios.get(INFO_BASE_URL+"info-server",{
            auth: {
                username: users.username,
                password: users.password
            }
        })
    }

}
export default new InfoServices();