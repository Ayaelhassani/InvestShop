import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { WalletService, Wallet } from '../../services/wallet.service';

@Component({
  selector: 'app-wallet-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './wallet-list.component.html',
  styleUrl: './wallet-list.component.css'
})
export class WalletListComponent implements OnInit {

  wallets: Wallet[] = [];

  constructor(private walletService: WalletService, private router: Router) {}
  ngOnInit(): void {
    this.loadWallets();
  }

  loadWallets(): void {
    this.walletService.getAllWallets().subscribe(data => {
      this.wallets = data;
    });
  }

  addToCart(walletId: number): void {
    this.walletService.addToCart(walletId).subscribe(() => {
      this.loadWallets();//Rechargement de la liste pour mettre a jour le bouton
    });
  }

  goToCart(): void {
    this.router.navigate(['/cart']);
  }
}