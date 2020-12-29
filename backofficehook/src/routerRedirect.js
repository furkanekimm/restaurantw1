export const redirectWithId = (pathname,id,history) => {
    history.push({
        pathname: pathname,
        state: {
            id: id
        }
    })
}

export const redirectWithProduct = (pathname,product,history) => {
    history.push({
        pathname: pathname,
        state: {
            product: product
        }
    })
}