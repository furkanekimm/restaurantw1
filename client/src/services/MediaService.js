import axios from 'axios';

const MEDIA_API_BASE_URL = "http://localhost:8080/file/";

class MediaService {
    getAllMedia(users) {
        return axios.get(MEDIA_API_BASE_URL + "list/", {
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
    }

}
export default new MediaService();