import { useEffect, useState,useContext } from 'react';
import {Context} from '../../contexts/Context';
import ProductService from '../../services/ProductService';
import HeaderComponent from '../HeaderComponent';
import Loading from "../Loading";
import {useHistory} from 'react-router-dom';
const ListReports = (props) => {
    const history = useHistory();
    const{users,authorizeControl}=useContext(Context);
    const [carts, setCarts] = useState([]);
    const [loading, setLoading] = useState(true);
    async function getAllCarts() {
        const controlContext =await authorizeControl(); 
        if(await controlContext===false){
            history.push('/');
        }
        const res = await ProductService.listCarts(users);
        if (res.status == '200') {
            setCarts(res.data);
        }
        setLoading(false)
    }

    useEffect(() => {
        getAllCarts();
    }, [])

    return (
        <>
            {loading == true ?
                <Loading /> :

                <div>
                    <HeaderComponent />
                    <div className="container" style={{ marginTop: "15px" }}>
                        <div className="row" style={{ overflow: "auto", height: "37rem" }}>
                            <table className="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Date</th>
                                        <th>Piece</th>
                                        <th>Total</th>
                                        <th>Product ID</th>
                                        <th>Table ID</th>
                                        <th>Category ID</th>
                                        <th>Waiter ID</th>
                                        <th>Customer ID</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        carts.map(
                                            cart =>
                                                <tr>
                                                    <td>{cart.createDate}</td>
                                                    <td>{cart.piece}</td>
                                                    <td>{cart.total}</td>
                                                    <td>{cart.productId}</td>
                                                    <td>{cart.tableId}</td>
                                                    <td>{cart.categoryId}</td>
                                                    <td>{cart.waiterId}</td>
                                                    <td>{cart.customerId}</td>
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
export default ListReports;