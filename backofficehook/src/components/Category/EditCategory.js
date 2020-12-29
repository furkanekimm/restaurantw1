import { useEffect, useRef, useState ,useContext} from "react";
import { useHistory } from 'react-router-dom';
import MediaService from "../../services/MediaService";
import HeaderComponent from '../HeaderComponent';
import ProductService from '../../services/ProductService';
import {Context} from '../../contexts/Context';
import Loading from "../Loading";
import {createNotification} from '../ErrorHandling';
const EditCategory = (props) => {
    const{users,lang}=useContext(Context);
    const [loading, setLoading] = useState(true);
    const history = useHistory();
    const refImage = useRef();
    const [medias, setMedias] = useState([]);
    const [category, setCategory] = useState({
        id: history.location.state?.id, name: '', description: '',  media: ''
    });


    const { id, name, description, media } = category;

    const showImage = async (e) => {
        await setCategory({ ...category, media: medias.filter(media => media.id == refImage.current.value)[0] })
        e.preventDefault();
    }

    const saveCategory = async (e) => {
        e.preventDefault();
        let category = {
            id: id,
            name: name,
            description: description,
            media:media
        };
        ProductService.updateCategory(category,users).then(res=>{
            createNotification(res.status,'update',lang);
            history.push('/categories');
        }).catch(({response})=>{
            createNotification(response.data.errorCode,0,lang);
        });
        
    }

    const changeNameHandler = (event) => {
        setCategory({ ...category, name: event.target.value })
    }

    const changeDescriptionHandler = (event) => {
        setCategory({ ...category, description: event.target.value })
    }

    async function getCategoryByID() {
        ProductService.getCategoryByID(id,users).then(res=>{
            if(res.status===200){
                setCategory({
                    ...category,
                    name: res.data.name,
                    description: res.data.description,
                    fileContent: res.data.media.fileContent,
                    media: res.data.media
                })
            }
        }).catch(
            ({response})=>{
                createNotification(response.data.errorCode,0,lang)
            }
        );
    }

    async function getAllMedias() {
        const res = await MediaService.getAllMedia(users);
        console.log(res);
        if (res.status == 200) {
            await setMedias(res.data);
        }
    }
    async function getData() {
        await getAllMedias();
        await getCategoryByID();
        setLoading(false)
    }

    useEffect(() => {
        getData();
    }, [])

    const cancel = () => {
        history.push('/categories');
    }

    return (
        <>
            {loading == true ?
                <Loading /> :
                <div>
                    <HeaderComponent />
                    <div className="container">
                        <div className="row">
                            <div className="card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Add Category</h3>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group ">
                                            <label> Category Image </label>
                                            <div className="row">
                                                <div className="col-md-6">
                                                    <select onChange={(e) => showImage(e)}
                                                        className="form-control" id="option" ref={refImage}>
                                                        {
                                                            medias.map(
                                                                mediaNew =>
                                                                    <option key={mediaNew.id} selected={mediaNew.id === media.id}
                                                                        value={mediaNew.id}>{mediaNew.name} </option>
                                                            )
                                                        }
                                                    </select>
                                                </div>
                                                <div className="col-md-4 offset-md-2">
                                                    <div className="">
                                                        <img src={'data:image/png;base64,' + media.fileContent}
                                                            width="45rem" height="39rem"
                                                        />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="form-group">
                                            <label> Category Name </label>
                                            <input placeholder="Category Name" name="name" className="form-control"
                                                value={name} onChange={(e) => changeNameHandler(e)} />
                                        </div>
                                        <div className="form-group">
                                            <label> Category Description </label>
                                            <input placeholder="Product Name" name="description" className="form-control"
                                                value={description} onChange={(e) => changeDescriptionHandler(e)} />
                                        </div>
                                        <button className="btn btn-success" onClick={(e) => saveCategory(e)}>Save</button>
                                        <button className="btn btn-danger" onClick={cancel}
                                            style={{ marginLeft: "10px" }}>Cancel
                                    </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            }
        </>
    )
}
export default EditCategory;