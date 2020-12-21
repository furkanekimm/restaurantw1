import axios from 'axios';

const MEDIA_API_BASE_URL = "http://localhost:8080/file/";

class MediaService {
    addMedia(data, users) {
        return axios.post(MEDIA_API_BASE_URL + "add/", data, {
            mode: 'no-cors',
            auth: {
                username: users.username,
                password: users.password
            }
        })
    }

    getAllMedia(users) {
        return axios.get(MEDIA_API_BASE_URL + "list/", {
            auth: {
                username: users.username,
                password: users.password
            }
        })
    }

}
export default new MediaService();