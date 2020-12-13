import axios from 'axios';

const WAITER_BASE_URL = "http://localhost:8080/waiter/"

class WaiterService{
    addWaiter(waiter){
        return axios.post(WAITER_BASE_URL+"add",waiter,{
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }

    getAllWaiters(){
        return axios.get(WAITER_BASE_URL+"list",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        })
    }
    deleteWaiter(id){
        return axios.delete(WAITER_BASE_URL+id,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        })
    }
    updateWaiter(waiter){
        return axios.put(WAITER_BASE_URL,waiter,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        })
    }
}
export default new WaiterService();