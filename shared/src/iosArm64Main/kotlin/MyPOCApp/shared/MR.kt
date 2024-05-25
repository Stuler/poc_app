package MyPOCApp.shared

public actual object MR {
    public actual object strings : ResourceContainer<StringResource>
    public actual object plurals : ResourceContainer<PluralsResource>
    public actual object images : ResourceContainer<ImageResource>
    public actual object fonts : ResourceContainer<FontResource>
    public actual object files : ResourceContainer<FileResource>
    public actual object colors : ResourceContainer<ColorResource>
    public actual object assets : ResourceContainer<AssetResource>

}