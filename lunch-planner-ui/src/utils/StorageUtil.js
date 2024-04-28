/**
 * save user info
 * @param {object} userInfo 
 */
export const saveUser = (userInfo) => {
    sessionStorage.setItem('userInfo', JSON.stringify(userInfo));
}

/**
 * get user info
 */
export const getUser = () => {
    let userInfo = sessionStorage.getItem('userInfo');
    if (userInfo === undefined || userInfo === null) {
        return null;
    }
    return JSON.parse(userInfo);
}

/**
 * check user is auth or not
 */
export const isAuthenticated = () => {
    let userInfo = sessionStorage.getItem('userInfo');
    if (userInfo === undefined || userInfo === null) {
        return false;
    }
    return true;
}

/**
 * remove user info
 */
export const removeUser = () => {
    sessionStorage.removeItem('userInfo');
}