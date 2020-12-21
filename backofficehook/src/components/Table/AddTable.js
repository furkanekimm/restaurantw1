import { useState,useContext } from "react";
import {Context} from '../../contexts/Context';
import { useHistory } from "react-router-dom";
import TableService from '../../services/TableService';
import HeaderComponent from '../HeaderComponent';

const AddTable = (props) => {
    const{users}=useContext(Context);
    const history = useHistory();
    const [table, setTable] = useState({
        name: '',
        tablePiece: 0
    });

    const { name, tablePiece } = table;

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
            tablePiece:tablePiece
        }
        const res = await TableService.addPlace(table,users);
        if(res.status=='200'){
            history.push('/tables');
        }

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
