import axios from 'axios'

const TABLE_BASE_URL = "http://localhost:8080/table"
const PLACE_BASE_URL = "http://localhost:8080/place";

class TableService{
    getPlace(){
        return axios.get(PLACE_BASE_URL+"/list",{
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }

    getPlaceById(){

    }
}
export default new TableService();