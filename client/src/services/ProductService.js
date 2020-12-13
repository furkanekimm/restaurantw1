import axios from 'axios';

const PRODUCT_API_BASE_URL = "http://localhost:8080/product"
const CART_BASE_URL ="http://localhost:8080/"
const CATEGORY_API_BASE_URL = "http://localhost:8080/category/"


class ProductService{

   
    listAllProduct(){
        return axios.get(PRODUCT_API_BASE_URL, {
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

 /*   listAllCategory(){
        return axios.get(PRODUCT_API_BASE_URL+"/category", {
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }*/

    listByCategory(id){
        return axios.get(PRODUCT_API_BASE_URL+"/category/"+id,{
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

    saleButton(Carts){
        return axios.post(CART_BASE_URL+"carts/add",Carts,{
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }

    listCategories(){
        return axios.get(CATEGORY_API_BASE_URL,{
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }


}
export default new ProductService()