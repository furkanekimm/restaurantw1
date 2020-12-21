import axios from 'axios';

const TABLE_API_BASE_URL ="http://localhost:8080/table/";
const PLACE_API_BASE_URL ="http://localhost:8080/place/";

class TableService{


    addPlace(place,users){
        console.log(place);
        return axios.post(PLACE_API_BASE_URL+"add",place,{
            auth:{
                username: users.username,
                password: users.password
            }
        });

    }

    getAllPlaces(users){
        return axios.get(PLACE_API_BASE_URL+"list",{
            auth:{
                username: users.username,
                password: users.password
            }
        });
    }

    addTable(table,id,users){
        return axios.post(TABLE_API_BASE_URL+"add",table,{
            params:{
                id:id
            },
            auth:{
                username: users.username,
                password: users.password
            }
        });
    }

    updateTable(table,id,users){
        return axios.put(TABLE_API_BASE_URL+"update",table,{
            params:{
                id:id
            },
            auth:{
                username: users.username,
                password: users.password
            }
        });
    }

    deleteTable(id,users){
        return axios.delete(TABLE_API_BASE_URL+"delete/"+id,{
            auth:{
                username: users.username,
                password: users.password
            }
        })
    }

    updatePlaceRest(place,users){
        return axios.put(PLACE_API_BASE_URL+"update",place,{
            auth:{
                username: users.username,
                password: users.password
            }
        })
    }

    deletePlaceRest(id,users){
        return axios.delete(PLACE_API_BASE_URL+"delete/"+id,{
            auth:{
                username: users.username,
                password: users.password
            }
        })
    }

    getTableById(id,users){
        return axios.get(PLACE_API_BASE_URL+id,{
            auth:{
                username: users.username,
                password: users.password
            }
        })
    }



}
export default new TableService()