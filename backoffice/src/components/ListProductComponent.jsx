import React, {Component} from 'react';
import ProductService from '../services/ProductService';
import HeaderComponent from "./HeaderComponent";

class ListProductComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            products: [],
            categories: []

        }
        this.getProduct = this.getProduct.bind(this);
        this.editProduct = this.editProduct.bind(this);
        this.deleteProduct = this.deleteProduct.bind(this);
        this.viewProduct = this.viewProduct.bind(this);
    }

    viewProduct(product) {
        this.props.history.push({
            pathname: '/viewproduct',
            state: {
                product: product
            }
        })
    }

    deleteProduct(id) {
        ProductService.deleteProduct(id).then(res => {
            this.setState({products: this.state.products.filter(products => products.id !== id)});
        }).then(res => {
            window.location.reload();
        });
    }

    getProduct(id) {
        this.setState({products: this.state.products.filter(product => product.categoryId == id)});
    }

    addProduct = (e) => {
        this.props.history.push('/add');
    }

    editProduct(id, catId) {
        this.props.history.push({
            pathname: '/update',
            state: {
                id: id,
                catId: catId
            }
        });
    }

    componentDidMount() {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('/');
        }

        ProductService.listAllProduct().then((res) => {
            console.log(res.data)
            this.setState({products: res.data})
        });

    }

    render() {
        return (

            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <button style={{marginTop: "15px", marginBottom: "10px"}} className="btn btn-primary"
                                onClick={this.addProduct}>Add
                            Product
                        </button>
                        <button style={{marginTop: "15px", marginBottom: "10px", marginLeft: "10px"}}
                                className="btn btn-primary"
                                onClick={() => window.location.reload()}>ListAll
                        </button>
                    </div>
                    <div className="row" style={{overflow: "auto", height: "37rem"}}>
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
                                this.state.products.map(
                                    product =>
                                        <tbody>
                                        <tr key={product.id}>
                                            <td onClick={() => this.getProduct(product.categoryId)}><a
                                                href="#">{product.categoryName[0].name}</a>
                                            </td>
                                            <td>{product.productName}</td>
                                            <td>{product.description}</td>
                                            <td>{product.price}</td>
                                            <td><img
                                                style={{marginLeft: "0rem", marginRight: "10px"}}
                                                src={'data:image/png;base64,' + product.media.fileContent}
                                                width="45rem" height="39rem"
                                            /></td>
                                            <td>
                                                <button
                                                    onClick={() => this.editProduct(product.id, product.categoryId)}
                                                    className="btn btn-info"> Update
                                                </button>
                                                <button style={{margin: "6px"}}
                                                        onClick={() => this.deleteProduct(product.id)}
                                                        className="btn btn-danger"> Delete
                                                </button>
                                                <button style={{margin: "6px"}}
                                                        onClick={() => this.viewProduct(product)}
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
        );
    }
}

export default ListProductComponent;