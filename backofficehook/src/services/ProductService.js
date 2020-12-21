import axios from 'axios';

const PRODUCT_API_BASE_URL = "http://localhost:8080/product/"
const CATEGORY_API_BASE_URL = "http://localhost:8080/category/"
class ProductService {

    addProduct(product, users) {
        return axios.post(PRODUCT_API_BASE_URL + 'add', product, {
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }

    listAllProduct(users) {
        return axios.get(PRODUCT_API_BASE_URL, {
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }

    getProductById(id, users) {
        return axios.get(PRODUCT_API_BASE_URL + id, {
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }

    updateProduct(product, users) {
        return axios.put(PRODUCT_API_BASE_URL + 'update/', product, {
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }

    deleteProduct(id, users) {
        return axios.delete(PRODUCT_API_BASE_URL + "delete/" + id, {
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }

    addCategory(category, users) {
        return axios.post(CATEGORY_API_BASE_URL + "add", category, {
            auth: {
                username: users.username,
                password: users.password
            }
        });

    }

    listCategories(users) {
        return axios.get(CATEGORY_API_BASE_URL, {
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }
    listCarts(users) {
        return axios.get("http://localhost:8080/carts/list", {
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }

    deleteCategory(id, users) {
        return axios.delete(CATEGORY_API_BASE_URL + id, {
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }

    updateCategory(category, users) {
        return axios.put(CATEGORY_API_BASE_URL + "update/", category, {
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }

    getCategoryByID(id, users) {
        return axios.get(CATEGORY_API_BASE_URL + id, {
            auth: {
                username: users.username,
                password: users.password
            }
        });
    }

}

export default new ProductService()