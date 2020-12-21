import { useEffect, useState, useContext } from "react";
import { Context } from '../../contexts/Context';
import ProductService from "../../services/ProductService";
import HeaderComponent from "../HeaderComponent";
import { useHistory } from 'react-router-dom';
import Loading from "../Loading";
const ListProduct = (props) => {
    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [product, setProduct] = useState([]);
    const [category, setCategory] = useState([]);
    const { users,authorizeControl } = useContext(Context);
    
    async function getAllProduct() {
        const res = await ProductService.listAllProduct(users);
        await setProduct(res.data);
    }

    async function getAllCategories() {
        const res = await ProductService.listCategories(users);
        await setCategory(res.data);
    }

    async function getData() {
        const controlContext =await authorizeControl(); 
        if(await controlContext===false){
            history.push('/');
        }
        await getAllCategories();
        await getAllProduct();
        setLoading(false)
    }

    useEffect(() => {
        getData();
    }, [])

    const getProduct = async (id) => {
        console.log(product.filter(product=> product.categoryName.filter(category=> category.id===id)[0]));
        await setProduct(product.filter(product=> product.categoryName.filter(category=> category.id===id)[0]));
    }

    const editProduct = (id) => {
        history.push({
            pathname: '/updateproduct',
            state: {
                id: id
            }
        })
    }

    const deleteProduct = async (id) => {
        const res = await ProductService.deleteProduct(id, users);
        if (res.status = '200') {
            setProduct(product.filter(product => product.id != id))
        }
    }

    const viewProduct = (product) => {
        history.push({
            pathname: '/viewproduct',
            state: {
                product: product
            }
        })
    }

    const addProduct = () => {
        history.push('/addproduct')
    }

    const getAllList=async (e)=>{
        await getAllProduct();
    }
    return (

        <div>
            {loading == true ?
                <Loading /> :
                <div>
                    <HeaderComponent />
                    <div className="container">
                        <div className="row">
                            <button style={{ marginTop: "15px", marginBottom: "10px" }} className="btn btn-primary"
                                onClick={(e) => addProduct(e)}>Add
                                Product
                        </button>
                            <button style={{ marginTop: "15px", marginBottom: "10px", marginLeft: "10px" }}
                                className="btn btn-primary" onClick={(e)=>getAllList(e)}
                            >ListAll
                        </button>
                        </div>
                        <div className="row" style={{ overflow: "auto", height: "37rem" }}>
                            <table className="table table-borderless  table-bordered ">
                                <thead className="table-bordered">
                                    <tr>
                                        <th>Category</th>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th>Price</th>
                                        <th>Image</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                {
                                    product.map(
                                        product =>
                                            <tbody>
                                                <tr key={product.id} className="border">
                                                    <td>
                                                        {
                                                            product.categoryName.map(
                                                                category=>
                                                                <a href="#" style={{marginBottom:"10px"}} onClick={(e) => getProduct(category.id)}> {category.name}<br/></a>
                                                            )
                                                        }
                                                    </td>
                                                    <td>{product.productName}</td>
                                                    <td>{product.description}</td>
                                                    <td>{product.price}</td>
                                                    <td><img
                                                        style={{ marginLeft: "0rem", marginRight: "10px" }}
                                                        src={'data:image/png;base64,' + product.media.fileContent}
                                                        width="45rem" height="39rem"
                                                    /></td>
                                                    <td>
                                                        <button
                                                            onClick={(e) => editProduct(product.id)}
                                                            className="btn btn-info"> Update
                                                </button>
                                                        <button style={{ margin: "6px" }}
                                                            onClick={(e) => deleteProduct(product.id)}
                                                            className="btn btn-danger"> Delete
                                                </button>
                                                        <button style={{ margin: "6px" }}
                                                            onClick={(e) => viewProduct(product)}
                                                            className="btn btn-success"> View
                                                </button>
                                                    </td>
                                                </tr>

                                            </tbody>
                                    )
                                }
                            </table>
                        </div>
                    </div>
                </div>
            }
        </div>
    )
}
export default ListProduct;