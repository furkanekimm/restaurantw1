import {  useEffect, useState } from "react";
import { useHistory } from 'react-router-dom';
import CustomerService from "../services/CustomerService";
import HeaderComponent from "../componenets/HeaderComponent";
import { redirectWithId } from "../routerRedirect"
import Pagination from "react-js-pagination";
const ListCustomers = (props) => {
    const history = useHistory();
    const [customers, setCustomers] = useState([]);
    const [page, setPage] = useState(0);
    const [total, setTotal] = useState();

    useEffect(() => {
        handlePageChange(0);
    }, []);

    const deleteCustomer = async (id) => {
        const res = await CustomerService.deleteCustomer(id);
        if (res.status !== 200) {
            window.alert("Customer silinemedi...!");
        }
        setCustomers(customers.filter(customer => customer.id !== id))
        window.alert(id+"id'li customer silindi..!");
    }

    const handlePageChange = async (pageNumber) => {
        const pageNum = pageNumber ;
        const res = await CustomerService.customersWithPage(pageNum);
        const { data } = res;
        if (res.status !== 200 || !data) {
            window.alert("sunucudan veriler alınamadı...");
            return;
        }

        setCustomers(data.content);
        setTotal(data.totalElements);
        setPage(pageNum);
    }

    const mapProduct = () => {

        return (customers.map(
            customer =>
                <tbody>
                <tr key={customer.id} className="border" style={{ height: "30px" }}>
                    <img
                        style={{height: "45px",marginLeft:"20px",borderRadius:"10px"}}
                        src={'data:image/png;base64,' + customer.media.fileContent}
                    />
                    <td>{customer.name}</td>
                    <td>{customer.lastName}</td>
                    <td>{customer.address}</td>
                    <td>{customer.phone}</td>
                    <td>
                        <button
                            onClick={(e) => redirectWithId("/update-customer", customer.id, history)}
                            className="btn btn-info"> Update
                        </button>
                        <button style={{ margin: "6px" }}
                                onClick={(e) => deleteCustomer(customer.id)}
                                className="btn btn-danger"> Delete
                        </button>
                    </td>
                </tr>
                </tbody >
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
            <div>
                <table className="table table-borderless  table-bordered " style={{marginLeft:"14rem"}}>
                    <thead className="table-bordered">
                    <tr>
                        <th>Image</th>
                        <th>Category</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Actions</th>
                    </tr>
                    </thead >
                    {
                        mapProduct()
                    }
                </table>
                <div className="row" style={{ marginLeft: "30rem" }}>
                    <Pagination
                        itemClass="page-item"
                        linkClass="page-link"
                        activePage={page+1}
                        itemsCountPerPage={5}
                        totalItemsCount={total}
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
                    <button style={{ marginTop: "15px", marginBottom: "10px" ,marginLeft:"14rem"}} className="btn btn-primary"
                            onClick={(e) => history.push('/add-customer')}>Add
                        Customer
                    </button>

                </div>
                <div className="row" >
                    {
                        getTableAndPagination()
                    }
                </div>
            </div>
        </div>
    )
}
export default ListCustomers;