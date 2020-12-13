import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import MediaService from "../services/MediaService";

class UpdateCategoryComponent extends Component {
    constructor(props) {
        super(props);
        this.state={
            id:this.props.history.location.state?.category.id,
            category:this.props.history.location.state?.category,
            name:'',
            description:'',
            urlToImage:'',
            media: [],
            selectedImage: [],
            image: '',
            fileContent:''
        }
        this.changeNameHandler=this.changeNameHandler.bind(this);
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.updateCategory=this.updateCategory.bind(this);
    }
    updateCategory=(event)=>{
        event.preventDefault();
        let category= {
            id:this.state.id,
            name:this.state.name,
            description:this.state.description,
            urlToImage: this.state.urlToImage,
            mediaId:document.getElementById('option').value
        }
        ProductService.updateCategory(category).then(res=>{
            this.props.history.push({
                pathname:'/listcategories'
            });
        })
    }
    showImage(id) {
        this.setState({selectedImage: this.state.media.filter(media => media.id == document.getElementById('option').value)})
    }


    componentDidMount() {
        MediaService.getAllMedia().then((res) => {
            this.setState({media: res.data})
        })
        this.setState({
            name:this.props.history.location.state?.category.name,
            description:this.props.history.location.state?.category.description,
            urlToImage:this.props.history.location.state?.category.urlToImage,
            image:this.props.history.location.state?.category.fileContent
        });
    }


    cancel() {
        this.props.history.push('/listcategories');
    }

    changeNameHandler=(event)=>{
        this.setState({name:event.target.value});
    }

    changeDescriptionHandler=(event)=>{
        this.setState({description:event.target.value});
    }


    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-center">Update Category</h3>
                        <div className="card-body">
                            <form>
                                <div className="form-group ">
                                    <label> Category Image </label>
                                    <div className="row">
                                        <div className="col-md-6">
                                            <select  onChange={() => this.showImage()}
                                                    className="form-control" id="option">
                                                {
                                                    this.state.media.map(
                                                        media =>
                                                            <option key={media.id} selected={this.state.category.mediaId==media.id}
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
                                    <label for="Name"> Category Name </label>
                                    <input placeholder="Category Name" name="Name" className="form-control"
                                           value={this.state.name} onChange={this.changeNameHandler}/>
                                </div>
                                <div className="form-group">
                                    <label>Category Description</label>
                                    <input placeholder="Category Description" name="Description" className="form-control"
                                           value={this.state.description} onChange={this.changeDescriptionHandler}/>
                                </div>
                                <button className="btn btn-success" onClick={this.updateCategory}> Update</button>
                                <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                                        style={{marginLeft: "10px"}}>Cancel
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default UpdateCategoryComponent;