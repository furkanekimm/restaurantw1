import { useEffect, useRef, useState, useContext } from "react";
import { Context } from '../../contexts/Context';
import { useHistory } from 'react-router-dom';
import MediaService from "../../services/MediaService";
import HeaderComponent from '../HeaderComponent';
import ProductService from '../../services/ProductService';
const AddCategory = (props) => {

    const { users } = useContext(Context);
    const history = useHistory();
    const refImage = useRef();

    const [category, setCategory] = useState({
        name: '', description: ''
    });
    const [selectedImage,setSelectedImage]=useState([]);
    const [media,setMedia]=useState([]);

    const { name, description} = category;

    const showImage = async (e) => {
        console.log(media);
        console.log(refImage.current.value);
        const refId= refImage.current.value;
        e.preventDefault();
        await setSelectedImage(media.filter(media => media.id == refId)[0])
        console.log(selectedImage);
        console.log(media.filter(media => media.id == refId)[0]);
    }

    const saveCategory = async (e) => {
        e.preventDefault();
        let category = {
            name: name,
            description: description,
            mediaId: refImage.current.value,
        };
        const res = await ProductService.addCategory(category,users);
        if (res.status == '200') {
            history.push('/categories');
        }

    }

    const changeNameHandler = (event) => {
        setCategory({ ...category, name: event.target.value })
    }

    const changeDescriptionHandler = (event) => {
        setCategory({ ...category, description: event.target.value })
    }

    async function getAllMedia() {
        const res = await MediaService.getAllMedia(users)
        await setMedia(res.data);
        await setSelectedImage(res.data[0]);
    }

    useEffect(() => {
        getAllMedia();
    }, [])

    const cancel = () => {
        history.push('/categories');
    }

    return (
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
                                                            <option key={media.id}
                                                                value={media.id}>{media.name} </option>
                                                    )
                                                }
                                            </select>
                                        </div>
                                        <div className="col-md-4 offset-md-2">
                                            <div className="">
                                                <img src={'data:image/png;base64,' + selectedImage.fileContent}
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
    )
}
export default AddCategory;