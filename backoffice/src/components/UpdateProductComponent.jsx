import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import HeaderComponent from "./HeaderComponent";
import MediaService from "../services/MediaService";

class UpdateProductComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.history.location.state?.id,
            categoryId: this.props.history.location.state?.catId,
            productName: '',
            description: '',
            price: '',
            categories: [],
            multiSelect: [],
            media: [],
            selectedImage: [],
            image: '',
            fileContent:''
        }
        this.changeCategoryHandler = this.changeCategoryHandler.bind(this);
        this.changeProductNameHandler = this.changeProductNameHandler.bind(this);
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.changePriceHandler = this.changePriceHandler.bind(this);
        this.updateProduct = this.updateProduct.bind(this);
        this.changeCateId = this.changeCateId.bind(this);
    }

    showImage(id) {
        this.setState({selectedImage: this.state.media.filter(media => media.id == document.getElementById('option').value)})
    }

    async changeMultiCate(id) {
        if (this.state.multiSelect.includes(id) !== true) {
            this.state.multiSelect.push(id);
            console.log(this.state.multiSelect)
        } else {
            for (let i = 0; i < this.state.multiSelect.length; i++) {
                if (id === this.state.multiSelect[i]) {
                    this.state.multiSelect.splice(i, 1);
                    console.log(this.state.multiSelect)
                }
            }
        }


    }

    componentDidMount() {
        MediaService.getAllMedia().then((res) => {
            this.setState({media: res.data})
        })
        ProductService.getProductById(this.state.id).then((res) => {
            let product = res.data;
            console.log(this.state.cateid)
            this.setState({
                id: product.id,
                productName: product.productName,
                description: product.description,
                price: product.price,
                multiSelect: product.categoryId,
                image:product.media.fileContent,
            });
            this.changeMultiCate = this.changeMultiCate.bind(this);
        });
        ProductService.listCategories().then(res => {
            this.setState({categories: res.data})
        });
    }

    updateProduct = (e) => {
        e.preventDefault();
        let Product = {
            id:this.state.id,
            productName: this.state.productName,
            description: this.state.description,
            price: this.state.price,
            urlToImage: this.state.urlToImage,
            categoryId: this.state.multiSelect,
            media:this.state.selectedImage[0]
        };
        ProductService.updateProduct(Product).then(res => {
            this.props.history.push('/product')
        });

    }
    changeCategoryHandler = (event) => {
        this.setState({category: event.target.value})
    }

    changeIdHandler = (event) => {
        this.setState({id: event.target.value})
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
        this.setState({categoryId: event.target.value})
    }

    cancel() {
        this.props.history.push('/product');
    }


    render() {
        const multiSelect = this.state.multiSelect;
        const categories = this.state.categories;
        const selected = [];
        for (let i = 0; i < categories.length; i++) {
            if (multiSelect.includes(categories[i].id)) {
                selected.push(<div className="row col-md -12"><label className="col-md-12"  ><input type="checkbox" defaultChecked="true"
                                                                            onClick={() => this.changeMultiCate(categories[i].id)}/>{categories[i].name}
                </label></div>)
            } else {
                selected.push(<div className="row col-md -12"><label className="col-md-12"><input  type="checkbox"
                                                                            onClick={() => this.changeMultiCate(categories[i].id)}/>{categories[i].name}
                </label></div>)
            }

        }
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Update Product</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">

                                        <label> Category </label>
                                        <div className="checkbox" style={{height: "5.5rem", overflow: "auto"}}>
                                            {selected}
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
                                        <input placeholder="Product Price" name="price" className="form-control"
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
                                    <button className="btn btn-success" onClick={this.updateProduct}> Update</button>
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

export default UpdateProductComponent;