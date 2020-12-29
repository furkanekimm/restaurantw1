import { useEffect, useState, useContext } from "react";
import Pagination from "react-js-pagination";
import { Context } from '../../contexts/Context';
import ProductService from "../../services/ProductService";
import HeaderComponent from "../HeaderComponent";
import { useHistory } from 'react-router-dom';
import Loading from "../Loading";
import { redirectWithId, redirectWithProduct } from "../../routerRedirect";

const ListProduct = (props) => {
    const history = useHistory();
    const [page, setPage] = useState(0);
    const [totalProduct, setTotalProduct] = useState();
    const [loading, setLoading] = useState(true);
    const [product, setProduct] = useState([]);
    const [category, setCategory] = useState([]);
    const { users, authorizeControl } = useContext(Context);

    async function getAllCategories() {
        const res = await ProductService.listCategories(users);
        setCategory(res.data);
    }

    async function getData() {
        const controlContext = await authorizeControl();
        if (await controlContext === false) {
            history.push('/');
        }
        await getAllCategories();
        await handlePageChange(1);
        await setLoading(false);
    }

    useEffect(() => {
        getData();
    }, [])

    const deleteProduct = async (id) => {
        const res = await ProductService.deleteProduct(id, users);
        if (res.status = '200') {
            setProduct(product.filter(product => product.id != id))
        }
    }

    const handlePageChange = async (pageNumber) => {

        const pageNum = pageNumber - 1;
        const res = await ProductService.getProductWithPage(pageNum, users);
        console.log(res);
        const { data } = res;
        if (res.status !== 200 || !data) {
            window.alert("sunucudan veriler alınamadı...");
            return;
        }

        setPage(pageNum);
        setProduct(data.content);
        console.log(product);
        setTotalProduct(data.totalElements);
    }

    if (loading === true) {
        return <Loading />
    }
    
    const mapProduct = () => {
        
        return (product.map(
            productItem =>
                <tbody>
                    <tr key={productItem.id} className="border" style={{ height: "95px" }}>
                        <td>
                            {
                            productItem.category.map(
                                category =>
                                    <div><a href="#" style={{ marginBottom: "2px",textSize:"5px" }} > {category.name}</a></div>
                            )
                        }
                        </td>
                        <td >{productItem.productName}</td>
                        <td>{productItem.description}</td>
                        <td>{productItem.price}</td>
                        <td><img
                            style={{ marginLeft: "0rem", marginRight: "10px" }}
                            src={'data:image/png;base64,' + productItem.media.fileContent}
                            width="45rem" height="39rem"
                        /></td>
                        <td>
                            <button
                                onClick={(e) => redirectWithId("/updateproduct", productItem.id, history)}
                                className="btn btn-info"> Update
                        </button>
                            <button style={{ margin: "6px" }}
                                onClick={(e) => deleteProduct(productItem.id)}
                                className="btn btn-danger"> Delete
                        </button>
                            <button style={{ margin: "6px" }}
                                onClick={(e) => redirectWithProduct("/viewproduct", productItem, history)}
                                className="btn btn-success"> View
                        </button>
                        </td>
                    </tr>

                </tbody>
        ))
    }

    const getTableAndPagination = () => {

        // if (!product || !product.category) {
        //     return (
        //         <div>
        //             <label>Kayıt Bulunamadı...!</label>
        //         </div>
        //     )
        // }

        return (
            <div className="row col-md-12">
                <table className="table table-borderless  table-bordered col-md-12 " style={{width:"69.3rem"}}>
                    <thead className="table-bordered">
                        <tr>
                            <th>Category</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Image</th>
                            <th>Actions</th>
                        </tr>
                    </thead >
                    {
                        mapProduct()
                    }
                </table>
                <div className="row" style={{ marginLeft: "25rem" }}>
                    <Pagination
                        itemClass="page-item"
                        linkClass="page-link"
                        activePage={page + 1}
                        itemsCountPerPage={5}
                        totalItemsCount={totalProduct}
                        pageRangeDisplayed={5}
                        onChange={(e) => handlePageChange(e)}
                    />
                </div>
            </div>
        )
    }

    return (
        <div>
            <HeaderComponent />
            <div className="container">
                <div className="row">
                    <button style={{ marginTop: "15px", marginBottom: "10px" }} className="btn btn-primary"
                        onClick={(e) => history.push('/addproduct')}>Add
                        Product
                        </button>

                </div>
                {/* style={{ overflow: "auto", height: "37rem" }} */}
                <div className="row" >
                    {
                        getTableAndPagination()
                    }
                </div>
            </div>
        </div>
    )
}
export default ListProduct;