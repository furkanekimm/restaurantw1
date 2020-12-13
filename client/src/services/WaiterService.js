import axios from 'axios';

const WAITER_BASE_URL = "http://localhost:8080/waiter/"

class WaiterService{
    getAllWaiters(){
        return axios.get(WAITER_BASE_URL+"list",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        })
    }
}
export default new WaiterService();