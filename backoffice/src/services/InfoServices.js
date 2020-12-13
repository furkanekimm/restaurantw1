import axios from 'axios';

const INFO_BASE_URL = "http://localhost:8080/info/";
const INFO_MYSQL_BASE_URL = "http://localhost:8082/info/"

class InfoServices{
    getInfo(){
        return axios.get(INFO_BASE_URL+"info-server",{
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }
    getInfoProd(){
        return axios.get(INFO_MYSQL_BASE_URL+"info-server",{
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }

}
export default new InfoServices();