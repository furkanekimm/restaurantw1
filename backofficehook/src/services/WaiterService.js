import axios from 'axios';

const WAITER_BASE_URL = "http://localhost:8080/waiter/"

class WaiterService{
    addWaiter(waiter,users){
        return axios.post(WAITER_BASE_URL+"add",waiter,{
            auth:{
                username: users.username,
                password: users.password
            }
        })
    }

    getAllWaiters(users){
        return axios.get(WAITER_BASE_URL+"list",{
            auth:{
                username: users.username,
                password: users.password
            }
        })
    }
    deleteWaiter(id,users){
        return axios.delete(WAITER_BASE_URL+id,{
            auth:{
                username: users.username,
                password: users.password
            }
        })
    }
    updateWaiter(waiter,users){
        return axios.put(WAITER_BASE_URL,waiter,{
            auth:{
                username: users.username,
                password: users.password
            }
        })
    }

    getWaiterByID(id,users){
        return axios.get(WAITER_BASE_URL+id,{
            auth:{
                username: users.username,
                password: users.password
            }
        })
    }
}
export default new WaiterService();