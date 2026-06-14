import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { WalletService, Cart } from '../../services/wallet.service';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit {

  cart: Cart = { items: [], total: 0 };
  constructor(private walletService: WalletService, private router: Router) {}
  ngOnInit(): void {
    this.loadCart();
  }
  loadCart(): void {
    this.walletService.getCart().subscribe(data => {
      this.cart = data;
    });
  }
  
  removeFromCart(walletId: number): void {
    this.walletService.removeFromCart(walletId).subscribe(() => {
      this.loadCart();
    });
  }

  saveCart(): void {
    this.walletService.saveCart().subscribe(() => {
      alert('Panier sauvegardé !');
    });
  }

  goToWallets(): void {
    this.router.navigate(['/wallets']);
  }
}