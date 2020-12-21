import { useEffect, useRef, useState ,useContext} from "react";
import { useHistory } from 'react-router-dom';
import MediaService from "../../services/MediaService";
import HeaderComponent from '../HeaderComponent';
import ProductService from '../../services/ProductService';
import {Context} from '../../contexts/Context';
import Loading from "../Loading";
const EditCategory = (props) => {
    const{users}=useContext(Context);
    const [loading, setLoading] = useState(true);
    const history = useHistory();
    const refImage = useRef();
    const [media, setMedia] = useState([]);
    const [category, setCategory] = useState({
        id: history.location.state?.id, name: '', description: '', fileContent: '', mediaId: ''
    });


    const { id, name, description, fileContent, mediaId } = category;

    const showImage = async (e) => {
        await setCategory({ ...category, fileContent: media.filter(media => media.id == refImage.current.value)[0].fileContent })
        e.preventDefault();
    }

    const saveCategory = async (e) => {
        e.preventDefault();
        let category = {
            id: id,
            name: name,
            description: description,
            mediaId: refImage.current.value,
        };
        const res = await ProductService.updateCategory(category,users);
        history.push('/categories');
    }

    const changeNameHandler = (event) => {
        setCategory({ ...category, name: event.target.value })
    }

    const changeDescriptionHandler = (event) => {
        setCategory({ ...category, description: event.target.value })
    }

    async function getCategoryByID() {
        const response = await ProductService.getCategoryByID(id,users);
        await setCategory({
            ...category,
            name: response.data.name,
            description: response.data.description,
            fileContent: response.data.fileContent,
            mediaId: response.data.mediaId
        })
    }

    async function getAllMedias() {
        const res = await MediaService.getAllMedia(users);
        console.log(res);
        if (res.status == 200) {
            await setMedia(res.data);
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
                                                            media.map(
                                                                media =>
                                                                    <option key={media.id} selected={category.mediaId === media.id}
                                                                        value={media.id}>{media.name} </option>
                                                            )
                                                        }
                                                    </select>
                                                </div>
                                                <div className="col-md-4 offset-md-2">
                                                    <div className="">
                                                        <img src={'data:image/png;base64,' + fileContent}
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