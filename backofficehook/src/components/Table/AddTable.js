import { useState,useContext,useRef,useEffect } from "react";
import {Context} from '../../contexts/Context';
import { useHistory } from "react-router-dom";
import TableService from '../../services/TableService';
import HeaderComponent from '../HeaderComponent';
import MediaService from "../../services/MediaService";

const AddTable = (props) => {
    const refImage = useRef();
    const [selectedImage,setSelectedImage]=useState([]);
    const{users}=useContext(Context);
    const history = useHistory();
    const[medias,setMedias]=useState([]);
    const [table, setTable] = useState({
        name: '',
        tablePiece: 0,
        media:''
    });

    const { name, tablePiece } = table;

    const showImage = async (e) => {
        const refId= refImage.current.value;
        e.preventDefault();
        await setSelectedImage(medias.filter(media => media.id == refId)[0])
    }

    const changeHandler=(e)=>{
        setTable({...table,[e.target.name]:e.target.value})
    }

    const cancel=(e)=>{
        history.push('/tables');
    }

    const saveTable=async (e)=>{
        e.preventDefault();

        let table={
            name:name,
            tablePiece:tablePiece,
            media:selectedImage
        }
        const res = await TableService.addPlace(table,users);
        if(res.status=='200'){
            history.push('/tables');
        }

    }

    async function getMedias(){
        const res = await MediaService.getAllMedia(users);
        if(res.status===200){
            setMedias(res.data);
            setSelectedImage(res.data[0])
        }
    }
    useEffect(()=>{
        getMedias();
    },[])


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
                                    <label> Table Image </label>
                                    <div className="row">
                                        <div className="col-md-6">
                                            <select onChange={(e) => showImage(e)}
                                                className="form-control" id="option" ref={refImage}>
                                                {
                                                    medias.map(
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
                                        value={name} onChange={(e) => changeHandler(e)} />
                                </div>
                                <div className="form-group">
                                    <label> Table Piece </label>
                                    <input placeholder="Table Piece" name="tablePiece" className="form-control"
                                        value={tablePiece} onChange={(e) => changeHandler(e)} />
                                </div>
                                <button className="btn btn-success" onClick={(e) => saveTable(e)}>Save</button>
                                <button className="btn btn-danger" onClick={(e) => cancel(e)}
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
export default AddTable;
