import { Routes } from '@angular/router';
import { WalletListComponent } from './components/wallet-list/wallet-list.component';
import { CartComponent } from './components/cart/cart.component';

export const routes: Routes = [
  { path: '', redirectTo: 'wallets', pathMatch: 'full' },
  { path: 'wallets', component: WalletListComponent },
  { path: 'cart', component: CartComponent }
];