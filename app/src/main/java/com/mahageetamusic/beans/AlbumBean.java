/**
 * 
 */
package com.mahageetamusic.beans;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Kishore
 * 
 */
public class AlbumBean implements Parcelable {

	int categoryID;
	int albumID;
	String albumName;
	String simage;
	String limage;
	String description;
	String release_date;
	int tracks;
	String NIP;
	String IP;
	String ICDP;
	String shipping_Charges;
	int topAlbum;
	Drawable Sicon, Licon;


	/**
	 * @return the sicon
	 */
	public Drawable getSicon() {
		return Sicon;
	}

	/**
	 * @param sicon
	 *            the sicon to set
	 */
	public void setSicon(Drawable sicon) {
		Sicon = sicon;
	}

	/**
	 * @return the licon
	 */
	public Drawable getLicon() {
		return Licon;
	}

	/**
	 * @param licon
	 *            the licon to set
	 */
	public void setLicon(Drawable licon) {
		Licon = licon;
	}

	public AlbumBean(Parcel source) {
		// TODO Auto-generated constructor stub
		categoryID = source.readInt();
		albumID = source.readInt();
		albumName = source.readString();
		simage = source.readString();
		limage = source.readString();
		description = source.readString();
		release_date = source.readString();
		tracks = source.readInt();
		NIP = source.readString();
		IP = source.readString();
		ICDP = source.readString();
		shipping_Charges = source.readString();
		topAlbum = source.readInt();
		
	}

	public AlbumBean() {
		// TODO Auto-generated constructor stub
		// this.songlist = new ArrayList<Songs>();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(categoryID);
		dest.writeInt(albumID);
		dest.writeString(albumName);
		dest.writeString(simage);
		dest.writeString(limage);
		dest.writeString(description);
		dest.writeInt(tracks);
		dest.writeString(release_date);
		dest.writeString(NIP);
		dest.writeString(IP);
		dest.writeString(ICDP);
		dest.writeString(shipping_Charges);
		dest.writeInt(topAlbum);
	}

	public static final Creator CREATOR = new Creator() {

		@Override
		public AlbumBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new AlbumBean(source);
		}

		@Override
		public AlbumBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new AlbumBean[size];
		}

	};

	/**
	 * @return the categoryID
	 */
	public int getCategoryID() {
		return categoryID;
	}

	/**
	 * @param categoryID
	 *            the categoryID to set
	 */
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	/**
	 * @return the albumID
	 */
	public int getAlbumID() {
		return albumID;
	}

	/**
	 * @param albumID
	 *            the albumID to set
	 */
	public void setAlbumID(int albumID) {
		this.albumID = albumID;
	}

	/**
	 * @return the albumName
	 */
	public String getAlbumName() {
		return albumName;
	}

	/**
	 * @param albumName
	 *            the albumName to set
	 */
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	/**
	 * @return the simage
	 */
	public String getSimage() {
		return simage;
	}

	/**
	 * @param simage
	 *            the simage to set
	 */
	public void setSimage(String simage) {
		this.simage = simage;
	}

	/**
	 * @return the limage
	 */
	public String getLimage() {
		return limage;
	}

	/**
	 * @param limage
	 *            the limage to set
	 */
	public void setLimage(String limage) {
		this.limage = limage;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the release_date
	 */
	public String getRelease_date() {
		return release_date;
	}

	/**
	 * @param release_date
	 *            the release_date to set
	 */
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	/**
	 * @return the tracks
	 */
	public int getTracks() {
		return tracks;
	}

	/**
	 * @param tracks
	 *            the tracks to set
	 */
	public void setTracks(int tracks) {
		this.tracks = tracks;
	}

	/**
	 * @return the nIP
	 */
	public String getNIP() {
		return NIP;
	}

	/**
	 * @param string
	 *            the nIP to set
	 */
	public void setNIP(String string) {
		NIP = string;
	}

	/**
	 * @return the iP
	 */
	public String getIP() {
		return IP;
	}

	/**
	 * @param iP
	 *            the iP to set
	 */
	public void setIP(String iP) {
		IP = iP;
	}

	/**
	 * @return the iCDP
	 */
	public String getICDP() {
		return ICDP;
	}

	/**
	 * @param iCDP
	 *            the iCDP to set
	 */
	public void setICDP(String iCDP) {
		ICDP = iCDP;
	}

	/**
	 * @return the shipping_Charges
	 */
	public String getShipping_Charges() {
		return shipping_Charges;
	}

	/**
	 * @param shipping_Charges
	 *            the shipping_Charges to set
	 */
	public void setShipping_Charges(String shipping_Charges) {
		this.shipping_Charges = shipping_Charges;
	}

	/**
	 * @return the topAlbum
	 */
	public int getTopAlbum() {
		return topAlbum;
	}

	/**
	 * @param topAlbum
	 *            the topAlbum to set
	 */
	public void setTopAlbum(int topAlbum) {
		this.topAlbum = topAlbum;
	}

}
