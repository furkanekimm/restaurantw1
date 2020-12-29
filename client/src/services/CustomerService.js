import axios from 'axios';
const CUSTOMER_BASE_URL = "http://localhost:8080/customer";

class CustomerService{

    customersWithPage(page){
        return axios.get(CUSTOMER_BASE_URL+"/page",{
            params:{
                page:page
            },
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }

    deleteCustomer(id){
        return axios.delete(CUSTOMER_BASE_URL+"/"+id,{
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }

    addCustomer(customer){
        return axios.post(CUSTOMER_BASE_URL,customer,{
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }

    getCustomerByName(name,page){
        return axios.get(CUSTOMER_BASE_URL+"/page-name",{
            params:{
                name:name,
                page:page
            },
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }

        })
    }

    getCustomerByID(id){
        return axios.get(CUSTOMER_BASE_URL+"/"+id,{
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }

    updateCustomer(customer){
        return axios.post(CUSTOMER_BASE_URL,customer,{
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }


}
export default new CustomerService();