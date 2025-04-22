const API_KEY = "b0fc5e8f10d9dfbf63a13d206b608406";
const URL = 'https://api.themoviedb.org/3'

export const getMovies=async()=>{
    const response = await fetch(`${URL}/movie/popular?api_key=${API_KEY}`)
    const data = await response.json()
    return data.results
}

export const searchMovies = async (query) => {
    const response = await fetch(
      `${URL}/search/movie?api_key=${API_KEY}&query=${encodeURIComponent(
        query
      )}`
    );
    const data = await response.json();
    return data.results;
  };