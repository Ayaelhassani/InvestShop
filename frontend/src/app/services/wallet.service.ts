import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

//Interface du wallet
export interface Wallet {
  id: number;
  name: string;
  assetGroup: string;
  gainsLastYear: number;
  price: number;
  inCart: boolean;
}

//Interface du panier
export interface Cart {
  items: Wallet[];
  total: number;
}

@Injectable({
  providedIn: 'root'
})
export class WalletService {

  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {} 
  getAllWallets(): Observable<Wallet[]> {//Recuperation des wallets
    return this.http.get<Wallet[]>(`${this.apiUrl}/wallets`);
  }
  getCart(): Observable<Cart> {//Recuperation du panier
    return this.http.get<Cart>(`${this.apiUrl}/cart`);
  }
  addToCart(walletId: number): Observable<void> {//Ajout du wallet dans le panier
    return this.http.post<void>(`${this.apiUrl}/cart/add/${walletId}`, {});
  }
  removeFromCart(walletId: number): Observable<void> {//Suppression du wallet du panier
    return this.http.delete<void>(`${this.apiUrl}/cart/remove/${walletId}`);
  }
  saveCart(): Observable<void> {//Suavegarde
    return this.http.post<void>(`${this.apiUrl}/cart/save`, {});
  }
}