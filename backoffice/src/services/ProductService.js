import axios from 'axios';

const PRODUCT_API_BASE_URL = "http://localhost:8080/product/"
const CATEGORY_API_BASE_URL = "http://localhost:8080/category/"
class ProductService {

    addProduct(product) {
        return axios.post(PRODUCT_API_BASE_URL + 'add', product, {
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

    listAllProduct() {
        return axios.get(PRODUCT_API_BASE_URL, {
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

    getProductById(id) {
        return axios.get(PRODUCT_API_BASE_URL + id, {
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

    updateProduct(product) {
        return axios.put(PRODUCT_API_BASE_URL + 'update/', product, {
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

    deleteProduct(id) {
        return axios.delete(PRODUCT_API_BASE_URL + "delete/" + id, {
            auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

    addCategory(category){
        return axios.post(CATEGORY_API_BASE_URL+"add",category,{
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });

    }

    listCategories(){
        return axios.get(CATEGORY_API_BASE_URL,{
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }
    listCarts(){
        return axios.get("http://localhost:8080/carts/list",{
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

    deleteCategory(id){
        return axios.delete(CATEGORY_API_BASE_URL+id,{
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

    updateCategory(category){
        return axios.put(CATEGORY_API_BASE_URL+"update/",category,{
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

}

export default new ProductService()