import axios from 'axios';

const TABLE_API_BASE_URL ="http://localhost:8080/table/";
const PLACE_API_BASE_URL ="http://localhost:8080/place/";

class TableService{


    addPlace(place){
        console.log(place);
        return axios.post(PLACE_API_BASE_URL+"add",place,{
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });

    }

    getAllPlaces(){
        return axios.get(PLACE_API_BASE_URL+"list",{
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

    addTable(table,id){
        return axios.post(TABLE_API_BASE_URL+"add",table,{
            params:{
                id:id
            },
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

    updateTable(table,id){
        return axios.put(TABLE_API_BASE_URL+"update",table,{
            params:{
                id:id
            },
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        });
    }

    deleteTable(id){
        return axios.delete(TABLE_API_BASE_URL+"delete/"+id,{
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }

    updatePlaceRest(place){
        return axios.put(PLACE_API_BASE_URL+"update",place,{
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }

    deletePlaceRest(id){
        return axios.delete(PLACE_API_BASE_URL+"delete/"+id,{
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }



}
export default new TableService()