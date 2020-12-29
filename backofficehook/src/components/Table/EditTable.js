import { useEffect, useState,useContext,useRef } from "react";
import {Context} from '../../contexts/Context';
import TableService from "../../services/TableService";
import { useHistory } from "react-router-dom";
import HeaderComponent from '../HeaderComponent';
import Loading from "../Loading";
import MediaService from "../../services/MediaService";
const EditTable = (props) => {
    const refImage = useRef();
    const{users}=useContext(Context);
    const [loading, setLoading] = useState(true);
    const history = useHistory();
    const[medias,setMedias] = useState([]);
    const [table, setTable] = useState({
        id: history.location.state?.id,
        name: '',
        tablePiece: 0,
        media:''
    });

    const showImage = async (e) => {
        await setTable({...table,media:medias.filter(media => media.id == refImage.current.value)[0]})
        e.preventDefault();
    }

    const { id, name, tablePiece,media } = table;

    const changeHandler = (e) => {
        setTable({ ...table, [e.target.name]: e.target.value })
    }

    const cancel = (e) => {
        history.push('/tables');
    }

    const updateTable = async (e) => {
        e.preventDefault();
        let table = {
            id: id,
            name: name,
            tablePiece: tablePiece,
            media:media
        }
        const res = await TableService.updatePlaceRest(table,users);
        if (res.status == '200') {
            history.push('/tables');
        }
    }

    async function getTableById() {
        const res = await TableService.getTableById(id,users);
        if (res.status == '200') {
            setTable({ ...table, name: res.data.name, tablePiece: res.data.tablePiece,media:res.data.media })
        }
        setLoading(false)
        await MediaService.getAllMedia(users).then((res)=>{
            if(res.status===200){
                setMedias(res.data);
            }
        })
    }

    useEffect(() => {
        getTableById();

    }, [])

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
                                                        ref={refImage} className="form-control" id="option">
                                                        {
                                                            medias.map(
                                                                media =>
                                                                    <option key={media.id} selected={table.media.id == media.id}
                                                                        value={media.id}>{media.name} </option>
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
                                                value={name} onChange={(e) => changeHandler(e)} />
                                        </div>
                                        <div className="form-group">
                                            <label> Table Piece </label>
                                            <input placeholder="Table Piece" name="tablePiece" className="form-control"
                                                value={tablePiece} onChange={(e) => changeHandler(e)} />
                                        </div>
                                        <button className="btn btn-success" onClick={(e) => updateTable(e)}>Save</button>
                                        <button className="btn btn-danger" onClick={(e) => cancel(e)}
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
export default EditTable;