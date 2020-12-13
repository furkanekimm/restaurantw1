import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import HeaderComponent from "./HeaderComponent";
import MediaService from "../services/MediaService";

class CreateCategoryComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            name: '',
            description: '',
            urlToImage: '',
            media: [],
            selectedImage: [],
            image: ''
        }
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.saveCategory = this.saveCategory.bind(this);
        this.showImage = this.showImage.bind(this);
    }

    showImage(id) {
        this.setState({selectedImage: this.state.media.filter(media => media.id == document.getElementById('option').value)})
    }

    saveCategory = (e) => {
        e.preventDefault();
        let category = {
            name: this.state.name,
            description: this.state.description,
            mediaId: document.getElementById('option').value,
        };
        console.log('category => ' + JSON.stringify(category));
        console.log(document.getElementById('option').value)
        ProductService.addCategory(category).then(res => {
            this.props.history.push('/listcategories');
        });

    }
    changeNameHandler = (event) => {
        this.setState({name: event.target.value})
    }
    changeDescriptionHandler = (event) => {
        this.setState({description: event.target.value})
    }

    componentDidMount() {
        MediaService.getAllMedia().then((res) => {
            this.setState({media: res.data})
        })
        /*   this.setState({selectedImage:this.state.media[0]})*/
    }

    cancel() {
        this.props.history.push('/listcategories');
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add Category</h3>
                            <div className="card-body">
                                <form>
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
                                    <div className="form-group">
                                        <label> Category Name </label>
                                        <input placeholder="Category Name" name="name" className="form-control"
                                               value={this.state.name} onChange={this.changeNameHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label> Category Description </label>
                                        <input placeholder="Product Name" name="description" className="form-control"
                                               value={this.state.description} onChange={this.changeDescriptionHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveCategory}>Save</button>
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

export default CreateCategoryComponent;