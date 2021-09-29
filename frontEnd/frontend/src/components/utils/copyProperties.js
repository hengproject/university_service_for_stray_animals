export default function (source, dest) {
  for (let key in source) {
    if (dest[key] !== undefined) dest[key] = source[key];
  }
}
