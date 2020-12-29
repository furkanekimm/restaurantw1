import axios from 'axios';
const CUSTOMER_BASE_URL = "http://localhost:8080/customer";

class CustomerService{
    
    customersWithPage(users,page){
        return axios.get(CUSTOMER_BASE_URL+"/page",{
            params:{
                page:page
            },
            auth: {
                username: users.username,
                password: users.password
            }
        })
    }

    deleteCustomer(id,users){
        return axios.delete(CUSTOMER_BASE_URL+"/"+id,{
            auth: {
                username: users.username,
                password: users.password
            }
        })
    }

    addCustomer(customer,users){
        return axios.post(CUSTOMER_BASE_URL,customer,{
            auth: {
                username: users.username,
                password: users.password
            }
        })
    }

    updateCustomer(customer,users){
        return axios.post(CUSTOMER_BASE_URL,customer,{
            auth: {
                username: users.username,
                password: users.password
            }
        })
    }

    getCustomerByID(id,users){
        return axios.get(CUSTOMER_BASE_URL+"/"+id,{
            auth: {
                username: users.username,
                password: users.password
            }
        })
    }


}
export default new CustomerService();