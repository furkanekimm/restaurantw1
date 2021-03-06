import { useContext, useEffect, useState } from "react"
import { Context } from '../../contexts/Context';
import ProductService from '../../services/ProductService';
import Loader from 'react-loader-spinner';
import HeaderComponent from '../HeaderComponent';
import { useHistory } from 'react-router-dom';
import {createNotification} from '../ErrorHandling';
const ListCategory = () => {
    const { users, authorizeControl ,lang} = useContext(Context);
    const history = useHistory();
    const [category, setCategory] = useState({ categories: [], loading: false });

    const { categories, loading } = category;

    const editCategory = (id) => {
        history.push({
            pathname: '/updatecategory',
            state: {
                id: id
            }
        })
    }

    const deleteCategory = async (id) => {
        ProductService.deleteCategory(id, users).then(res=>{
            if (res.status === 200) {
                setCategory({ ...category, categories: categories.filter(category => category.id !== id) })
                createNotification(res.status,'delete',lang)
                console.log(lang.lang)
            }
        }).catch(({response})=>{
            createNotification(response,0,lang)
        });
        
    }

    const addCategory = () => {
        history.push({
            pathname: '/addcategory'
        });
    }
    async function getData() {
        const res = await authorizeControl();
        if (await res === false) {
            history.push('/');
        }
        setCategory({ ...category, loading: false });
        ProductService.listCategories(users).then((res) => {
            console.log(res.data)
            setCategory({ ...category, categories: res.data, loading: true })
        })
    }
    useEffect(() => {

        getData();
    }, []);
    return (
        <>

            <div>
                {!loading && <div style={{
                    width: '100%',
                    height: "100",
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    marginTop: '28%'
                }}>
                    <Loader type="TailSpin" color="#000" height="50" width="100" />
                </div> ||
                    <div>
                        <HeaderComponent />
                        <div className="container">
                            <div className="row">
                                <button style={{
                                    marginTop: "15px",
                                    marginBottom: "10px"
                                }} className="btn btn-primary" onClick={addCategory} >Add Category</button>
                            </div>
                            <div className="row" style={{ overflow: "auto", height: "37rem" }}>
                                <table className="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>ImageUrl</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            categories.map(
                                                category =>
                                                    <tr key={category.id}>
                                                        <td>{category.name}</td>
                                                        <td>{category.description}</td>
                                                        <td><img
                                                            style={{ marginLeft: "3rem", marginRight: "10px" }}
                                                            src={'data:image/png;base64,' + category.media.fileContent}
                                                            width="45rem" height="39rem"
                                                        /></td>
                                                        <td >
                                                            <button style={{ marginLeft: "3.3rem" }}
                                                                onClick={() => editCategory(category.id)}
                                                                className="btn btn-info"> Update
                                        </button>
                                                            <button style={{ marginLeft: "2rem" }}
                                                                onClick={() => deleteCategory(category.id)}
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
            </div>

        </>
    )
}
export default ListCategory;