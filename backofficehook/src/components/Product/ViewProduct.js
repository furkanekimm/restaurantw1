import { useEffect, useState } from "react";
import { useHistory } from 'react-router-dom';
import HeaderComponent from '../HeaderComponent';

const ViewProduct = (props) => {
    const history = useHistory();
    const [product, setProduct] = useState({
        productName: history.location.state?.product.productName,
        description: history.location.state?.product.description,
        price: history.location.state?.product.price,
        media: history.location.state?.product.media.fileContent
    })

    const { productName, description, price ,media} = product;

    return (
        <div>
            <div className="container">
                <HeaderComponent />
                <div className="row">
                    <h2 className="col-md-12 text-center">Product View Detail</h2>
                    <div className="col-md-12 mt-2">
                        <div className="card col-md-6 offset-md-3">
                            <img
                                style={{width: "32rem", height: "20rem"}} className="card-img mt-3"
                                src={'data:image/png;base64,' + media}
                            />
                            <div className="row col-md-12">
                                <h3 className="card-text col-md-6">Product Name</h3>
                                <h3 className="card-text col-md-6 text-center">{productName}</h3>
                            </div>
                            <div className="row col-md-12">
                                <h3 className="card-text col-md-6">Description</h3>
                                <h3 className="card-text col-md-6 text-center">{description}</h3>
                            </div>
                            <div className="row col-md-12">
                                <h3 className="card-text col-md-6">Product Price</h3>
                                <h3 className="card-text col-md-6 text-center">{price} ₺</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default ViewProduct;