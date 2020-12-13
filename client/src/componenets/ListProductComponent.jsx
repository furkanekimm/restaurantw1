import React, {Component} from 'react';
import ProductService from '../services/ProductService';
import './card-style.css';
import nextId from "react-id-generator";
import HeaderComponent from "./HeaderComponent";

class ListProductComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            categories: [],
            products: [],
            cart: {
                cartId: 0,
                productId: 0,
                piece: 1,
                productName: '',
                price: 0,
                total: 0,
            },
            carts: [],
            totalCart: 0,
            cartStorage: []
        }
        this.listProductByCategory = this.listProductByCategory.bind(this);
        this.goTables = this.goTables.bind(this);
    }

    goTables = (e) => {
        this.addStorage();
        this.props.history.push("/homepagee")
        e.preventDefault();

    }

    getStorage() {
        let order = [];
        if (localStorage.getItem(`${this.state.categoryId}-${this.state.tableId}`) !== null) {
            order = JSON.parse(localStorage.getItem(`${this.state.categoryId}-${this.state.tableId}`));
            this.setState({carts: order.carts, totalCart: order.totalCart})
            console.log(order);
        }
    }

    addStorage() {
        const order = {
            tableId: this.state.tableId,
            categoryId: this.state.categoryId,
            waiterId: this.state.waiterId,
            carts: this.state.carts,
            totalCart: this.state.totalCart
        }
        localStorage.setItem(`${this.state.categoryId}-${this.state.tableId}`, JSON.stringify(order));
    }

    listProductByCategory(e) {
        ProductService.listByCategory(e).then((res) => {
            this.setState({products: res.data});
        });
        this.render();
    }

    saleButton(Carts) {
        localStorage.removeItem(`${this.state.categoryId}-${this.state.tableId}`);
        console.log(Carts);
        ProductService.saleButton(Carts).then(res => {
            this.props.history.push({
                pathname: '/homepagee'
            })
        });
    }


    increasePiece(cart) {
        cart.piece += 1;
        cart.total = cart.total + cart.price;
        this.state.totalCart += cart.price;
        //this.setState({carts: this.state.carts.filter(carts => carts.id == cart.id)});
        this.setState([{...this.state.carts, [cart.cartId]: cart}])
    }

    decreasePiece(cart) {
        cart.piece -= 1;
        cart.total = cart.total - cart.price;
        this.state.totalCart -= cart.price;
        if (cart.piece == 0) {
            this.setState({carts: this.state.carts.filter(carts => carts.cartId !== cart.cartId)});
        } else {
            this.setState([{...this.state.carts, [cart.cartId]: cart}])
        }
    }

    addCarts(products) {
        this.state.totalCart += products.price;
        if (this.state.carts.filter(cart => cart.productId == products.id).length > 0) {
            var cart = this.state.carts.filter(cart => cart.productId == products.id)
            cart[0].piece += 1;
            cart[0].total = cart[0].total + cart[0].price;
            this.setState([{...this.state.carts, [cart[0].productId]: cart[0]}])

        } else {
            this.setState({
                cart: {
                    cartId: nextId(),
                    productId: products.id,
                    productName: products.productName,
                    price: products.price,
                    piece: 1,
                    total: products.price,
                    tableId: this.state.tableId,
                    categoryId: this.state.categoryId,
                    waiterId: this.state.waiterId
                }
            }, () => this.setState({carts: [...this.state.carts, this.state.cart]}))
        }


    }

    async loadPage() {
        const res = await ProductService.listCategories();
        await this.setState({categories: res.data});
        const loadProduct = await ProductService.listByCategory(this.state.categories[0].id);
        await this.setState({products: loadProduct.data});
    }

    componentDidMount() {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('/');
        }
        this.setState({
            tableId: this.props.history.location.state?.id,
            categoryId: this.props.history.location.state?.categoryId,
            waiterId: this.props.history.location.state?.waiterId,
            waiterName: this.props.history.location.state?.waiterName,
            placeName: this.props.history.location.state?.placeName,


        }, () => this.getStorage())
        ProductService.listCategories().then((res) => {
            //console.log(res.data);
            this.setState({categories: res.data});
            console.log(res.data);
        });
        this.loadPage();


    }

    render() {
        return (
            <div>

                <div className="container">
                    <nav className="navbar navbar-expand-lg navbar-light bg-light">
                        <button className="navbar-toggler" type="button" data-toggle="collapse"
                                data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01"
                                aria-expanded="false"
                                aria-label="Toggle navigation">
                        </button>
                        <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
                            {/*<a className="navbar-brand" href="#">User Control</a>*/}
                            <ul className="navbar-nav mr-5 mt-2 mt-lg-0"
                                style={{border: "1px solid green", borderRadius: "5px", backgroundColor: "#a6dced"}}>
                                <h5><span style={{color: "white"}}>Waiter </span>{this.state.waiterName}</h5>
                            </ul>
                            <ul className="navbar-nav mr-auto mt-2 mt-lg-0"
                                style={{border: "1px solid green", borderRadius: "5px", backgroundColor: "#a6dced"}}>
                                <h5><span
                                    style={{color: "white"}}>Table   </span>{this.state.placeName} {this.state.tableId}
                                </h5>
                            </ul>
                            <form className="form-inline my-2 my-lg-0">
                                <a onClick={this.goTables} href="/homepagee" className="navbar-toggler-icon"></a>
                            </form>
                        </div>
                    </nav>
                    <div className="row">
                        <div className="col-md-2">
                            <div className="list-group">
                                <a href="#" className="list-group-item list-group-item-action active">
                                    Categories
                                </a>
                                {
                                    this.state.categories.map(
                                        category =>
                                            <tr key={category}>
                                                <a href="#" className="list-group-item list-group-item-action"
                                                   onClick={() => this.listProductByCategory(category.id)}><img
                                                    style={{marginLeft:"2px",marginRight:"10px"}}
                                                    src={'data:image/png;base64,' + category.fileContent}
                                                    width="45rem" height="39rem"
                                                />{category.name}</a>
                                            </tr>
                                    )
                                }
                            </div>
                        </div>
                        <div className="col-md-5 list-scrool">
                            <div className="container-fluid d-flex justify-content-center">
                                <div className="row">
                                    {
                                        this.state.products.map(
                                            products =>
                                                <div style={{marginBottom: "20px"}} className="col-md-5">
                                                    <div className="card text-center">
                                                        <div className="overflow">
                                                            <img
                                                                style={{height: "130px"}} className="card-img-top"
                                                                src={'data:image/png;base64,' + products.media.fileContent}
                                                            />
                                                        </div>
                                                        <div className="card-body text-dark">
                                                            <h5 className="card-title">{products.productName}</h5>
                                                            <p className="card-text text-secondary">{products.description}</p>
                                                            <p className="card-text text-secondary">{products.price} ₺</p>
                                                            <button className="btn btn-info"
                                                                    onClick={() => this.addCarts(products)}>Add to Cart
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                        )
                                    }
                                </div>

                            </div>
                        </div>

                        <div className="card card-option col-md-4.8 ml-4">
                            <div className="card-header-tr">
                                <table className="table table-striped  table-css">
                                    <thead>
                                    <tr>
                                        <th>Increase</th>
                                        <th>Piece</th>
                                        <th>Name</th>
                                        <th>Total</th>
                                        <th>Decrease</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {
                                        this.state.carts.map(
                                            cart =>
                                                <tr className="border-0" key={cart.cartId}>
                                                    <td>
                                                        <button className="btn btn-success"
                                                                onClick={() => this.increasePiece(cart)}>+
                                                        </button>
                                                    </td>
                                                    <td>{cart.piece}</td>
                                                    <td>{cart.productName}</td>
                                                    <td>{cart.total} ₺</td>
                                                    <td>
                                                        <button className="btn btn-danger"
                                                                onClick={() => this.decreasePiece(cart)}>-
                                                        </button>
                                                    </td>
                                                </tr>
                                        )
                                    }
                                    </tbody>
                                </table>
                            </div>
                            <div>
                            </div>
                            <table>
                                <tr>
                                    <th className="righttotal">Total</th>
                                    <th>{this.state.totalCart} ₺</th>
                                    <th>&nbsp;&nbsp;&nbsp;</th>
                                    <th>
                                        <button className="btn btn-outline-danger btn-size"
                                                onClick={() => this.saleButton(this.state.carts)}>Payment
                                        </button>
                                    </th>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default ListProductComponent;