import { useEffect, useState, useContext } from "react";
import { Context } from '../../contexts/Context';
import { useHistory } from "react-router-dom";
import TableService from '../../services/TableService';
import HeaderComponent from '../HeaderComponent';
import Loading from "../Loading";
const ListTables = (props) => {
    const { users, authorizeControl } = useContext(Context);
    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [tables, setTables] = useState([]);

    async function getAllTables() {
        const controlContext = await authorizeControl();
        if (await controlContext === false) {
            history.push('/');
        }
        const res = await TableService.getAllPlaces(users);
        if (res.status == '200') {
            setTables(res.data);
        }
        setLoading(false)
    }

    useEffect(() => {
        getAllTables();
    }, [])

    const editPlace = (id) => {
        history.push({
            pathname: 'updatetable',
            state: {
                id: id
            }
        })
    }

    const addPlace = (e) => {
        history.push('/addtable')
    }

    const deletePlace = async (id) => {
        const res = await TableService.deletePlaceRest(id, users);
        if (res.status == '200') {
            setTables(tables.filter(table => table.id !== id));
        }
    }

    return (
        <>
            {loading == true ?
                <Loading /> :



                <div  >

                    <HeaderComponent />
                    <div className="container">
                        <div className="row">
                            <button style={{ marginTop: "15px", marginBottom: "10px" }} className="btn btn-primary" onClick={(e) => addPlace(e)}>Add
                            Place
                        </button>
                        </div>
                        <div className="row">
                            <table className="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Table Piece</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        tables.map(
                                            place =>
                                                <tr key={place.id}>
                                                    <td>{place.name}</td>
                                                    <td>{place.tablePiece}</td>
                                                    <td>
                                                        <button
                                                            onClick={(e) => editPlace(place.id)}
                                                            className="btn btn-info"> Update
                                                </button>
                                                        <button style={{ marginLeft: "6px" }}
                                                            onClick={(e) => deletePlace(place.id)}
                                                            className="btn btn-danger"> Delete
                                                </button>
                                                    </td>
                                                </tr>
                                        )
                                    }
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            }
        </>
    )
}
export default ListTables;