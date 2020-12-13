import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import HeaderComponent from "./HeaderComponent";
import MediaService from "../services/MediaService";

class CreateProductComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            productName: '',
            description: '',
            multiCategories: [],
            select: '',
            price: '',
            categories: [],
            cateid: 0,
            media:[],
            selectedImage: [],
            image: ''
        }
        this.changeUrlToImageHandler = this.changeUrlToImageHandler.bind(this);
        this.changeProductNameHandler = this.changeProductNameHandler.bind(this);
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.changePriceHandler = this.changePriceHandler.bind(this);
        this.changeCateId = this.changeCateId.bind(this);
        this.saveProduct = this.saveProduct.bind(this);
        this.changeMultiCate = this.changeMultiCate.bind(this);
        this.showImage = this.showImage.bind(this);
    }

    showImage(id) {
        this.setState({selectedImage: this.state.media.filter(media => media.id == document.getElementById('option').value)})
    }

    changeMultiCate(id) {
        if (this.state.multiCategories.includes(id) !== true) {
            this.state.multiCategories.push(id);
            console.log(this.state.multiCategories)
        } else {
            for (let i = 0; i < this.state.multiCategories.length; i++) {
                if (id === this.state.multiCategories[i]) {
                    this.state.multiCategories.splice(i, 1);
                    console.log(this.state.multiCategories)
                }
            }
        }


    }

    saveProduct = (e) => {
        e.preventDefault();
        let products = {
            productName: this.state.productName,
            description: this.state.description,
            price: this.state.price,
            urlToImage: this.state.urlToImage,
            categoryId: this.state.multiCategories,
            media:this.state.selectedImage[0]
        };
        ProductService.addProduct(products).then(res => {
            this.props.history.push('/product');
        });

    }

    componentDidMount() {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('/');
        }
        ProductService.listCategories().then(res => {
            this.setState({categories: res.data})
        });
        MediaService.getAllMedia().then((res) => {
            this.setState({media: res.data})
        });
    }

    changeUrlToImageHandler = (event) => {
        this.setState({urlToImage: event.target.value})
    }
    changeProductNameHandler = (event) => {
        this.setState({productName: event.target.value})
    }
    changeDescriptionHandler = (event) => {
        this.setState({description: event.target.value})
    }
    changePriceHandler = (event) => {
        this.setState({price: event.target.value})
    }
    changeCateId = (event) => {
        this.setState({cateid: event.target.value})
    }


    cancel() {
        this.props.history.push('/product');
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add Product</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label> Category </label>
                                        <div className="checkbox" style={{height: "4rem", overflow: "auto"}}>
                                            {
                                                this.state.categories.map(
                                                    category =>
                                                        <div className="row col-md -12">
                                                            <label><input type="checkbox" value=""
                                                                          onClick={() => this.changeMultiCate(category.id)}/>{category.name}
                                                            </label>
                                                        </div>
                                                )
                                            }
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label> Product Name </label>
                                        <input placeholder="Product Name" name="productName" className="form-control"
                                               value={this.state.productName} onChange={this.changeProductNameHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label> Description </label>
                                        <input placeholder="Description" name="description" className="form-control"
                                               value={this.state.description} onChange={this.changeDescriptionHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label> Price </label>
                                        <input placeholder="Price" name="price" className="form-control"
                                               value={this.state.price} onChange={this.changePriceHandler}/>
                                    </div>
                                    <div className="form-group ">
                                        <label> Category Image </label>
                                        <div className="row">
                                            <div className="col-md-6">
                                                <select onChange={() => this.showImage()}
                                                        className="form-control" id="option">
                                                    {
                                                        this.state.media.map(
                                                            media =>
                                                                <option key={media.id}
                                                                        value={media.id}>{media.name} </option>
                                                        )
                                                    }
                                                </select>
                                            </div>
                                            <div className="col-md-4 offset-md-2">
                                                {
                                                    this.state.selectedImage.map(
                                                        image =>
                                                            <div className="">
                                                                <img src={'data:image/png;base64,' + image.fileContent}
                                                                     width="45rem" height="39rem"
                                                                />
                                                            </div>
                                                    )
                                                }
                                            </div>
                                        </div>
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveProduct}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                                            style={{marginLeft: "10px"}}>Cancel
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default CreateProductComponent;